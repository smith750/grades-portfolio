import React from 'react';
import ReactDom from 'react-dom';
import store from './store';

const Grades = (props) => (
  <div>
    <h3>Grades</h3>
    <GradesDisplay/>
    <GradesInput/>
  </div>
);

const GradesDisplay = ({ grade }) => (
  <div>
    The Grade is {grade}
  </div>
);

const GradesInput = ({ grade, updateGrade }) => (
  <div>
    <label>Grade:
      <input type="text" value={grade} onChange={(event) => updateGrade(event.target.value)}/>
    </label>
  </div>
);

ReactDom.render(<Provider store={store}><Grades/></Provider>,document.getElementById('main'));
