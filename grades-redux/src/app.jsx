import React from 'react';
import ReactDom from 'react-dom';
import { Provider } from 'react-redux';
import store from './store';
import { GradesContainer } from './containers';

const GradesDisplay = ({ grade }) => (
  <div>
    The Grade is {grade}
  </div>
);

const GradesInput = ({ grade, onGradeChange }) => (
  <div>
    <label>Grade:
      <input type="text" value={grade} onChange={(event) => onGradeChange(event.target.value)}/>
    </label>
  </div>
);

const GradeRow = ({ grade, letterGrade, lowerBound }) => {
  let lowerGrade = grade * lowerBound;
  let oneLower = (1*lowerBound === 0.9) ? 0 : 1;
  let upperGrade = (grade * (1*lowerBound + 0.1)) - oneLower;
  return (
    <tr><td>{letterGrade}</td><td>{lowerGrade}</td><td>{upperGrade}</td></tr>
  );
};

const GradeFailureRow = ({grade, letterGrade}) => {
  let upperGrade = grade * (0.6 - 0.01);
  return (
    <tr><td>{letterGrade}</td><td colSpan="2">{upperGrade} and below</td></tr>
  );
};

const GradeTable = ({ grade }) => (
  <table>
    <tbody>
      <GradeRow grade={grade} letterGrade="A" lowerBound="0.9"/>
      <GradeRow grade={grade} letterGrade="B" lowerBound="0.8"/>
      <GradeRow grade={grade} letterGrade="C" lowerBound="0.7"/>
      <GradeRow grade={grade} letterGrade="D" lowerBound="0.6"/>
      <GradeFailureRow grade={grade} letterGrade="F"/>
    </tbody>
  </table>
);

const ContainedGradesDisplay = GradesContainer(GradesDisplay);
const ContainedGradesInput = GradesContainer(GradesInput);
const ContainedGradeTable = GradesContainer(GradeTable);

const Grades = () => (
  <div>
    <h3>Grades</h3>
    <ContainedGradesDisplay/>
    <ContainedGradesInput/>
    <ContainedGradeTable/>
  </div>
);

ReactDom.render(<Provider store={store}><Grades/></Provider>,document.getElementById('main'));
