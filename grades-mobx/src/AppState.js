import { observable, computed } from 'mobx';

class AppState {
  @observable grade = "100";
  @observable delta = 0;

  constructor() {
  }

  changeGrade(newGrade) {
    this.grade = newGrade;
    const newGradeInt = parseFloat(newGrade);
    this.delta = (newGradeInt <= 10)
      ? 1
      : (newGradeInt >= 40)
        ? 0
        : this.delta;
  }

  toggleDelta() {
    console.log("changing delta");
    this.delta = (this.delta === 0) ? 1 : 0;
  }

  @computed get shouldDisplayGradeTable() {
    return /^\d+\.*\d*$/.test(this.grade);
  }

  @computed get deltaDiff() {
    return (this.delta === 0) ? 1 : 0.1;
  }

  @computed get deltaChecked() {
    return this.delta !== 0
  }
}

export default AppState;
