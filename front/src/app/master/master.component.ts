import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as $ from 'jquery'

@Component({
  selector: 'master-component',
  templateUrl: './master.component.html',
  styleUrls: ['./master.component.css']
})
export class MasterComponent {

  selectedAssignedSupportRequest: any
  assignedSupportRequestList = []

  selectedAcceptedSupportRequest: any
  acceptedSupportRequestList = []

  selectedSupportRequest: any

  selectedAssignedDiagnosticsRequest: any
  assignedDiagnosticsRequestList = []

  selectedAcceptedDiagnosticsRequest: any
  acceptedDiagnosticsRequestList = []
  
  selectedDiagnosticsRequest: any

  selectedDeviceFromAssignedDiagnosticsRequest: any
  selectedDeviceFromAcceptedDiagnosticsRequest: any

  deviceFromStorageList: any
  selectedDeviceFromStorage: any

  diagnosticsResponse = {}
  diagnosticsResponseList = []
  diagnosticsResponseDescription = ""

  deviceBunchList = []
  deviceCount = 1

  newRequestName = ""
  newRequestDescription = ""

  masterName = ""

  serverDomain = 'http://localhost:8080'
  getRequestsPath = '/master/requests'
  postRequestPath = '/master/requests'
  acceptSupportRequestPath = '/master/accept-support-request'
  declineSupportRequestPath = '/master/accept-support-request'
  closeSupportRequestPath = '/master/close-support-request'
  acceptDiagnosticsRequestPath = '/master/accept-diagnostics-request'
  declineDiagnosticsRequestPath = '/master/accept-diagnostics-request'
  closeDiagnosticsRequestPath = '/master/close-diagnostics-request'
  createDeviceRequestPath = '/master/create-device-request'

  constructor(private http: HttpClient) {
  }

  authorize() {

    let onRequestListsReceived = (data) => {
      this.assignedSupportRequestList = data["assignedSupportRequestList"]
      this.acceptedSupportRequestList = data["acceptedSupportRequestList"]
      this.assignedDiagnosticsRequestList = data["assignedDiagnosticsRequestList"]
      this.acceptedDiagnosticsRequestList = data["acceptedDiagnosticsRequestList"]
      this.deviceFromStorageList = data["deviceFromStorageList"]
      this.diagnosticsResponseList = data["diagnosticsResponseList"]
    }

    let url = this.serverDomain + this.getRequestsPath
    let params = {
      masterName: this.masterName,
    }
    $.ajax({
      type: "GET",
      url: url,
      data: params,
      dataType: "json",
      success: onRequestListsReceived,
    });
  }

  acceptSupportRequest() {
    let url = this.serverDomain + this.acceptSupportRequestPath
    let data = {
      masterName: this.masterName,
      supportRequest: this.selectedAssignedSupportRequest,
    }
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  declineSupportRequest() {
    let url = this.serverDomain + this.declineSupportRequestPath
    let data = {
      masterName: this.masterName,
      supportRequest: this.selectedAssignedSupportRequest,
    }
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  closeSupportRequest() {
    let url = this.serverDomain + this.closeSupportRequestPath
    let data = {
      masterName: this.masterName,
      supportRequest: this.selectedAcceptedSupportRequest,
    }
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  acceptDiagnosticsRequest() {
    let url = this.serverDomain + this.acceptDiagnosticsRequestPath
    let data = {
      masterName: this.masterName,
      diagnosticsRequest: this.selectedAssignedDiagnosticsRequest,
    }
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  declineDiagnosticsRequest() {
    let url = this.serverDomain + this.declineDiagnosticsRequestPath
    let data = {
      masterName: this.masterName,
      diagnosticsRequest: this.selectedAssignedDiagnosticsRequest,
    }
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  // closeDiagnosticsRequest() {
  //   let url = this.serverDomain + this.closeDiagnosticsRequestPath
  //   let data = {
  //     masterName: this.masterName,
  //     diagnosticsRequest: this.selectedAcceptedDiagnosticsRequest,
  //   }
  //   $.ajax({
  //     type: "POST",
  //     url: url,
  //     headers: {
  //       'Content-Type': 'application/json',
  //       'Accept': 'application/json'
  //     },
  //     data: JSON.stringify(data),
  //     dataType: "json",
  //   });
  // }

  createDeviceRequest() {
    let url = this.serverDomain + this.createDeviceRequestPath
    // deviceBunchList = [for (x of selectedDeviceListFromStorage) {}]
    let deviceRequest = {
      deviceBunchList: this.deviceBunchList,
      supportRequest: this.selectedAcceptedSupportRequest,
    }
    // let data = {
    //   masterName: this.masterName,
    //   supportRequest: this.selectedAcceptedSupportRequest,
    // }
    let data = deviceRequest
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    });
  }

  addDeviceBunch() {
    let deviceBunch = {
      device: this.selectedDeviceFromStorage,
      count: this.deviceCount,
    }
    this.deviceBunchList.push(deviceBunch)
  }

  closeDiagnosticsRequest() {
    let url = this.serverDomain + this.closeDiagnosticsRequestPath
    this.diagnosticsResponse['diagnosticsRequest'] = this.selectedAcceptedDiagnosticsRequest
    this.diagnosticsResponse['description'] = this.diagnosticsResponseDescription
    let data = this.diagnosticsResponse
    $.ajax({
      type: "POST",
      url: url,
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      data: JSON.stringify(data),
      dataType: "json",
    })
  }

  ngOnInit() {
  }

}
