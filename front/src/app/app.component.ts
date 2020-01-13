import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { supportRequestList } from './data'

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent  {
  constructor(private http: HttpClient) { }

  ngOnInit() {

  }

}
