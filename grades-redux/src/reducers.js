import { Map } from 'immutable';
import { CHANGE_GRADE, TOGGLE_DELTA } from './actions';

const initialState = Map({
  "grade": "100",
  "delta": 0
});

export default function gradeApp(state = initialState, action) {
  switch (action.type) {
    case CHANGE_GRADE:
      const updatedDelta = (action.newGrade < 10)
        ? 1
        : (action.newGrade >= 40)
          ? 0
          : state.get("delta");
      return state.set("grade", action.newGrade).set("delta",updatedDelta);
    case TOGGLE_DELTA:
      const newDelta = (state.get("delta") === 0) ? 1 : 0;
      return state.set("delta", newDelta);
    default:
      return state;
  }
}
