import { changeGrade, toggleDelta } from './actions';
import { connect } from 'react-redux';

const gradeMapStateToProps = (state) => {
  return {
    grade: state.get("grade")
  };
};

const gradeMapDispatchToProps = (dispatch) => {
  return {
    onGradeChange: (newGrade) => {
        dispatch(changeGrade(newGrade));
    }
  };
};

export const GradesContainer = connect(gradeMapStateToProps, gradeMapDispatchToProps);

const deltaMapStateToProps = (state) => {
  let deltaChecked = state.get("delta") !== 0;
  return {
    delta: state.get("delta"),
    deltaChecked
  };
};

const deltaMapDispatchToProps = (dispatch) => {
  return {
    onDeltaChange: () => {
      dispatch(toggleDelta());
    }
  };
};

export const DeltaContainer = connect(deltaMapStateToProps, deltaMapDispatchToProps);

const gradeDeltaMapStateToProps = (state) => {
  let deltaDiff = (state.get("delta") === 0) ? 1 : 0.1;
  return {
    grade: state.get("grade"),
    delta: state.get("delta"),
    deltaDiff
  };
};

// nothing to dispatch
const gradeDeltaMapDispatchToProps = (dispatch) => {
  return {};
};

export const GradeDeltaContainer = connect(gradeDeltaMapStateToProps, gradeDeltaMapDispatchToProps);
