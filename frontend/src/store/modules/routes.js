// store/modules/routes.js

import { postRoute, updateRoute, getRouteInfoByRouteId, getRouteInfoWithComment, deleteRoute } from '@/api/routes.js'
// import accounts from '@/store/modules/accounts.js'
import images from './images'
// import accounts from './accounts'
import router from '@/router'


const state = {
  places: [],
  imgList: [],
  polyLine: null,
  routeDescription: '',
  xyPoints: [],
  whatTag: [],
  withTag: [],
  routeInfo: [],
  routeMethodType: '',
  clickedRouteId: null,
  routeInfoWithComment: [],
  postingCheck: false,
}

const getters = {
  places(state) {
    return state.places
  },
  imgList(state) {
    return state.imgList
  },
  polyLine(state) {
    return state.polyLine
  },
  routeDescription(state) {
    return state.routeDescription
  },
  xyPoints(state) {
    return state.xyPoints
  },
  whatTag(state) {
    return state.whatTag
  },
  withTag(state) {
    return state.withTag
  },
  routeInfo(state) {
    return state.routeInfo
  },
  routeMethodType(state) {
    return state.routeMethodType
  },
  clickedRouteId(state) {
    return state.clickedRouteId
  },
  routeInfoWithComment(state) {
    return state.routeInfoWithComment
  },
  postingCheck(state) {
    return state.postingCheck
  },
}

const mutations= {
  ADD_PLACE(state, newPlace) {
    state.places.push(newPlace)
  },
  ADD_IMAGE(state, newPlace) {
    state.imgList.push(newPlace.placeImg)
  },
  SEND_IMAGES_ARRAY(state) {
    console.log(state)
  },
  SET_POLYLINE(state, polyline) {
    state.polyLine = polyline
    // console.log(state)
  },
  REFRESH_PLACES(state, newPlaces) {
    state.places = newPlaces
  },
  UPDATE_DRAGGERBLE_ITEMS(state, event) {
    console.log(state.places)

    var selected = state.places[event.oldIndex]
    var step = event.oldIndex
    if( event.newIndex < event.oldIndex) {
      // 앞으로 온 경우
      for( ; step > event.newIndex ; step-- ) {
        state.places[step] = state.places[step-1]
      }
    } else {
      // 뒤로 간 경우
      for(  ; step < event.newIndex ; step++ ) {
        state.places[step] = state.places[step+1]
      }
    }
    state.places[event.newIndex] = selected
    console.log(state.places)

    // 이전 코드
  //  var temp = state.places[event.oldIndex]
  //  state.places[event.oldIndex] = state.places[event.newIndex]
  //  state.places[event.newIndex] = temp
  },
  CREATE_ROUTE_DESCRIPTION(state, routeDescription) {
    state.routeDescription = routeDescription
  },
  SET_XY_POINTS(state, points) {
    state.xyPoints = points
  },
  SET_WHAT_TAG(state, whatTag) {
    state.whatTag = whatTag
  },
  SET_WITH_TAG(state, withTag) {
    state.withTag = withTag
  },
  SET_ROUTE_INFO(state, res) {
    console.log('SET_ROUTE_INFO')
    state.routeInfo = res
  },
  CHANGE_METHOD_TYPE(state, type) {
    state.routeMethodType = type
  },
  SET_CLICKED_ROUTE_ID(state, routeId) {
    state.clickedRouteId = routeId
  },
  SET_ROUTE_INFO_WITH_COMMENT(state, res) {
    state.routeInfoWithComment = res
  },
  SET_SELECTED_ROUTE(state, res) {
    state.selectedRoute = res
  },
  CLEAR_ROUTE_INFO(state) {
    state.routeInfo = []
  }
}

// import axios from 'axios'
// actions는 Backend API와 통신하여 Data fetching과 같은 작업 수행
// 동기적인 작업 뿐 만 아니라 비동기적인 작업을 포함 가능
// 항상 Context가 인자로 넘어온다, 오직 mutation 매서드를 commit 호출해서 조작 가능
const actions = {
  addPlace ({ commit }, newPlace) {
    // console.log('잘 작동함 ㅇㅇ')
    // console.log(newPlace)
    commit('ADD_PLACE', newPlace)
    commit('ADD_IMAGE', newPlace)
  },
  refreshPlaces ( { commit }, newPlaces) {
    commit('REFRESH_PLACES', newPlaces)
  },
  updateDraggerbleItems ( { commit }, event ) {
    commit('UPDATE_DRAGGERBLE_ITEMS', event)
  },
  createRouteDescription( { commit }, event) {
    commit('CREATE_ROUTE_DESCRIPTION', event)
  },
  setXYPoints( {commit}, points) {
    commit('SET_XY_POINTS', points)
  },
  setWhatTag( {commit}, whatTag) {
    commit('SET_WHAT_TAG', whatTag)
  },
  setWithTag( {commit}, withTag) {
    commit('SET_WITH_TAG', withTag)
  },
  createRoute( {commit}, jwt) {
    console.log(commit)
    console.log(jwt)
    const newPlaces = []
    for (const place of state.places) {
      // console.log(`${place.createdOrder}번째 글내용 ${place.content}`)
      const tmpPlace = {
        createdOrder: place.createdOrder,
        imageUpload: place.imageUpload,
        placeImg : place.placeImg,
        lat : place.lat,
        lng : place.lng,
        title: place.content,
        isThumbnail : place.isThumbnail,
      } 
      newPlaces.push(tmpPlace)
    }

    const routeDescription = state.routeDescription
    const routeImg = images.state.routeImg
    const whatTag = state.whatTag
    const withTag = state.withTag

    // const access_token = accounts.state.jwt[0]
    console.log('access_token check')
    // console.log(access_token.getters.jwt[0])
    const CircularJSON = require('circular-json')
    console.log('시작했다')
      // params: { places, routeDescription, routeImg, whatTag, withTag }
    if (state.routeMethodType === 'put') {
      console.log('put요청')
      updateRoute(
        CircularJSON.stringify(
        {
          places: newPlaces,
          routeDescription: routeDescription,
          routeImg: routeImg,
          whatTag: whatTag,
          withTag: withTag,
          id: jwt[2]
        }), state.clickedRouteId, jwt,
        () => {
          console.log('success')
          state.places = []
          router.push({ name: 'HomeView' })
        },(error) => {
          console.log(error)
        }
      );
    } else {
      console.log('post요청')
      postRoute(
        CircularJSON.stringify(
        {
          places: newPlaces,
          routeDescription: routeDescription,
          routeImg: routeImg,
          whatTag: whatTag,
          withTag: withTag,
          id: jwt[2]
        }), jwt,
        () => {
          console.log('success')
          state.places = []
          state.postingCheck = true
          router.push({ name: 'HomeView' })
        },(error) => {
          console.log(error)
        }
      );
    }
  },
  fetchRouteInfo({ commit }, payload) {
    commit('CLEAR_ROUTE_INFO')
    console.log(payload)
    getRouteInfoByRouteId(payload.userId, payload.routeId, payload.access_token,
      (res) => {
        
        console.log('액션스~~~')
        console.log(res)
        commit('SET_ROUTE_INFO', res.data.success)
      }, (error) => {
        console.log(error)
    })
  },
  changeMethodType({ commit }, type) {
    commit('CHANGE_METHOD_TYPE', type)
  },
  setClickedRouteId({ commit }, routeId) {
    commit('SET_CLICKED_ROUTE_ID', routeId)
  },
  fetchRouteInfoWithComment({ commit }, routeId) {
    getRouteInfoWithComment(routeId,
      (res) => {
        console.log('여긴 루트 코멘트 받아온 데')
        console.log(res.data.success)
        commit('SET_ROUTE_INFO_WITH_COMMENT', res.data.success)
      }, (error) => {
        console.log(error)
    })
  },
  setSelectedRoute( {commit}, route){
    commit('SET_SELECTED_ROUTE', route)
  },
  clearRouteInfo({ commit }) {
    console.log('초기호ㅏ')
    commit('CLEAR_ROUTE_INFO')
  },

  deleteRouteActions({ dispatch }, payload) {
    console.log('루트 삭제 엑션스')
    console.log(payload)
    deleteRoute(payload.userId, payload.routeId, payload.access_token,
      (res) => {
        console.log(res)
        dispatch('clearRouteInfo')
      }, (error) => {
        console.log(error)
    })
  },
}

export default {
  state, getters, mutations, actions
}