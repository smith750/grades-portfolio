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

const Grades = () => (
  <div>
    <h3>Grades</h3>
    <ContainedGradesDisplay/>
    <ContainedGradesInput/>
  </div>
);

const ContainedGradesDisplay = GradesContainer(GradesDisplay);
const ContainedGradesInput = GradesContainer(GradesInput);

ReactDom.render(<Provider store={store}><Grades/></Provider>,document.getElementById('main'));
