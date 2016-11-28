import { Component } from '@angular/core';

export class GradeModel {
  grade: string;
}

@Component({
  selector: 'my-app',
  template: `
    <h2>Grades</h2>
    <div>The grade is {{grade}}</div>
    `
})

export class AppComponent {
  gradeModel: GradeModel = {
    grade: "100"
  };
}
