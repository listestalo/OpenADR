import * as types from '../constants/actionTypes';
import { history } from '../store/configureStore';

import { swaggerAction, jsonResponseContentType, multipartResponseContentType, saveData, parseJsonData } from './apiUtils';

export const loadEvent = (start, end) => {
  return swaggerAction(types.LOAD_EVENT, 
    (api) => {
      return api.apis[ 'demand-response-controller' ].listUsingGET(jsonResponseContentType);
    }, 
    parseJsonData
  );
}

export const createEvent = (dto) => {
	var params = { event: dto }
  return swaggerAction(types.CREATE_EVENT, 
    (api) => {
      return api.apis[ 'demand-response-controller' ].createUsingPOST(params, jsonResponseContentType);
    }, 
    parseJsonData
  );
}

export const loadEventDetail = (id) => {
	var params = { id: id };
  return swaggerAction(types.LOAD_EVENT_DETAIL, 
    (api) => {
      return api.apis[ 'demand-response-controller' ].readUsingGET(params, jsonResponseContentType);
    }, 
    parseJsonData
  );
}

export const activeEvent = (id) => {
  var params = { id: id };
  return swaggerAction(types.ACTIVE_EVENT, 
    (api) => {
      return api.apis[ 'demand-response-controller' ].activeUsingPOST(params, jsonResponseContentType);
    }, 
    parseJsonData
  );
}

export const cancelEvent = (id) => {
  var params = { id: id };
  return swaggerAction(types.CANCEL_EVENT, 
    (api) => {
      return api.apis[ 'demand-response-controller' ].cancelUsingPOST(params, jsonResponseContentType);
    }, 
    parseJsonData
  );
}

