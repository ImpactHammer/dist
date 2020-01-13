import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as $ from 'jquery'

@Component({
  selector: 'client-component',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent {

  selectedSupportRequest: any
  supportRequestList = []

  newRequestName = ""
  newRequestDescription = ""

  clientName = ""

  serverDomain = 'http://localhost:8080'
  getRequestsPath = '/support-requests'
  postRequestPath = '/support-requests'
  closeSupportRequestPath = '/client/close-support-request'

  constructor(private http: HttpClient) {
  }

  sendRequest() {
    let data = {
      name: this.newRequestName,
      description: this.newRequestDescription,
      clientName: this.clientName,
    }
    let url = this.serverDomain + this.postRequestPath
    $.ajax({
      type: "POST",
      url: url,
      data: data,
      dataType: "json"
    });
  }

  closeRequest() {
    let data = this.selectedSupportRequest
    let url = this.serverDomain + this.closeSupportRequestPath
    
    $.support.cors = true; 
    $.ajax({
      type: "POST",
      url: url,
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
      },
      crossDomain: true,
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  authorize() {
    let url = this.serverDomain + this.getRequestsPath
    let onSupportRequestListReceived = (data) => {
      this.supportRequestList = data
      // this.selectedSupportRequest = this.supportRequestList[this.supportRequestList.length - 1]
      console.log(this.selectedSupportRequest.description)
    }
    let params = {
      clientName: this.clientName,
    }
    $.ajax({
      type: "GET",
      url: url,
      data: params,
      dataType: "json",
      success: onSupportRequestListReceived,
    });
  }

  ngOnInit() {
  }

}
