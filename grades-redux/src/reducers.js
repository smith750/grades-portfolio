import { Map } from 'immutable';
import { CHANGE_GRADE, TOGGLE_DELTA } from './actions';

const initialState = Map({
  "grade": "100",
  "delta": 0
});

export default function gradeApp(state = initialState, action) {
  switch (action.type) {
    case CHANGE_GRADE:
      return state.set("grade", action.newGrade);
    default:
      return state;
  }
}
