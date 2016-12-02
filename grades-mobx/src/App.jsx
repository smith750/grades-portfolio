import React, { Component } from 'react';
import { observer } from 'mobx-react';
import DevTools from 'mobx-react-devtools';

@observer
class GradeDisplay extends Component {
  render() {
    return <div>Grade: {this.props.appState.grade}</div>;
  }
}

@observer
class GradeInput extends Component {
  render() {
    return <input type="text" value={this.props.appState.grade} onChange={(event) => this.changeGrade(event.target.value)} />;
  }

  changeGrade = (newGrade) => {
    this.props.appState.changeGrade(newGrade);
  }
}

@observer
class DeltaInput extends Component {
  render() {
    return <div>
    <label>
      Show decimal points?
      <input type="checkbox" checked={this.props.appState.deltaChecked} onChange={() => this.onDeltaChange()}/>
    </label>
    </div>
  };

  onDeltaChange() {
    console.log("calling toggle delta");
    this.props.appState.toggleDelta();
  }
}

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

@observer
class GradeTableDisplay extends Component {
  render() {
    return this.props.appState.shouldDisplayGradeTable
      ? <GradeTable grade={this.props.appState.grade} delta={this.props.appState.delta} deltaDiff={this.props.appState.deltaDiff}/>
      : <span/>
  }
}

class App extends Component {
  render() {
    return (
      <div>
        <GradeDisplay appState={this.props.appState}/>
        <GradeInput appState={this.props.appState}/>
        <DeltaInput appState={this.props.appState}/>
        <GradeTableDisplay appState={this.props.appState}/>
        <DevTools />
      </div>
    );
  }
};

export default App;
