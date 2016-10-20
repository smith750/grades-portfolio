export const CHANGE_GRADE = 'CHANGE_GRADE';
export const TOGGLE_DELTA = 'TOGGLE_DELTA';

export function changeGrade(newGrade) {
  return {
    type: CHANGE_GRADE,
    newGrade
  }
}

export function toggleDelta() {
  return {
    type: TOGGLE_DELTA
  }
}
