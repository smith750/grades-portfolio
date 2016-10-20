import React from 'react';
import ReactDom from 'react-dom';

const Grades = (props) => (
  <div>
    <h3>Grades</h3>
    <GradesDisplay/>
    <GradesInput/>
  </div>
);

const GradesDisplay = (props) => (
  <div>
    The Grade is
  </div>
);

const GradesInput = (props) => (
  <div>
    <label>Grade:
      <input type="text"/>
    </label>
  </div>
);

ReactDom.render(<Grades/>,document.getElementById('main'));
