import React from 'react';
import ReactDom from 'react-dom';
import { Provider } from 'react-redux';
import store from './store';
import { GradesContainer, DeltaContainer, GradeDeltaContainer } from './containers';

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

const DeltaInput = ({ deltaChecked, onDeltaChange }) => (
  <div>
    <label>
      Show decimal points?
      <input type="checkbox" checked={deltaChecked} onChange={() => onDeltaChange()}/>
    </label>
  </div>
);

const GradeRow = ({ grade, delta, deltaDiff, letterGrade, lowerBound }) => {
  let numLowerBound = parseFloat(lowerBound);
  let lowerGrade = grade * lowerBound;
  let oneLower = (numLowerBound === 0.9) ? 0 : deltaDiff;
  let upperGrade = (grade * (numLowerBound + 0.1)) - oneLower;
  return (
    <tr><td>{letterGrade}</td><td>{upperGrade.toFixed(delta)}</td><td>{lowerGrade.toFixed(delta)}</td></tr>
  );
};

const GradeFailureRow = ({grade, delta, deltaDiff, letterGrade}) => {
  let upperGrade = (grade * 0.6) - deltaDiff;
  return (
    <tr><td>{letterGrade}</td><td colSpan="2">{upperGrade.toFixed(delta)} and below</td></tr>
  );
};

const DisplayGradeTable = ({ grade, delta, deltaDiff }) => {
  return (/^\d+\.*\d*$/.test(grade))
    ? (<GradeTable grade={grade} delta={delta} deltaDiff={deltaDiff} />)
    : (<span />);
};

const GradeTable = ({ grade, delta, deltaDiff }) => (
  <table>
    <tbody>
      <GradeRow grade={grade} delta={delta} deltaDiff={deltaDiff} letterGrade="A" lowerBound="0.9"/>
      <GradeRow grade={grade} delta={delta} deltaDiff={deltaDiff} letterGrade="B" lowerBound="0.8"/>
      <GradeRow grade={grade} delta={delta} deltaDiff={deltaDiff} letterGrade="C" lowerBound="0.7"/>
      <GradeRow grade={grade} delta={delta} deltaDiff={deltaDiff} letterGrade="D" lowerBound="0.6"/>
      <GradeFailureRow grade={grade} delta={delta} deltaDiff={deltaDiff} letterGrade="F"/>
    </tbody>
  </table>
);

const ContainedGradesDisplay = GradeDeltaContainer(GradesDisplay);
const ContainedGradesInput = GradesContainer(GradesInput);
const ContainedDeltaInput = DeltaContainer(DeltaInput);
const ContainedGradeTable = GradeDeltaContainer(DisplayGradeTable);

const Grades = () => (
  <div>
    <h3>Grades</h3>
    <ContainedGradesInput/>
    <ContainedDeltaInput/>
    <ContainedGradeTable/>
  </div>
);

ReactDom.render(<Provider store={store}><Grades/></Provider>,document.getElementById('main'));
