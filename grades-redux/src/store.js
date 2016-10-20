import { createStore } from 'redux';
import gradeApp from './reducers';
import { changeGrade } from './actions';

let store = createStore(gradeApp);

export default store;
