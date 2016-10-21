import { changeGrade } from './actions';
import { connect } from 'react-redux';

const mapStateToProps = (state) => {
  return {
    "grade": state.get("grade")
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onGradeChange: (newGrade) => {
        dispatch(changeGrade(newGrade));
    }
  };
};

export const GradesContainer = connect(mapStateToProps, mapDispatchToProps);
