import { gradeChange } from './actions';
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

const GradesContainer = connect(mapStateToProps, mapDispatchToProps);

export default GradesContainer;
