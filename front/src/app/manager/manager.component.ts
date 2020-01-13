import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import * as $ from 'jquery'

@Component({
  selector: 'manager-component',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css'],
})
export class ManagerComponent {

  selectedPendingSupportRequest: any
  selectedMaster: any
  selectedDeviceInstanceFromStorage: any
  selectedDeviceRequest: any
  selectedDiagnosticsResponse: any
  selectedClient: any
  selectedClientDevice: any
  selectedClientDeviceList = []

  pendingSupportRequestList = []
  deviceRequestList = []
  totalDeviceInstanceList = []
  clientDeviceInstanceList = []
  freeMasterList = []
  clientList = []
  clientDevices = {}
  diagnosticsResponseList = []

  clientName = ""
  newDiagnosticsRequestName = ""

  serverDomain = 'http://localhost:8080'
  getListsPath = '/manager/lists'
  assignSupportRequestPath = '/manager/assign-support-request'
  assignDiagnosticsRequestPath = '/manager/assign-diagnostics-request'
  assignDeviceToClientPath = '/manager/assign-device-to-client'
  unassignDeviceFromClientPath = '/manager/unassign-device-from-client'
  closeDiagnosticsRequestPath = '/manager/close-diagnostics-request'

  constructor(private http: HttpClient) {
  }

  authorize() {

  }

  ngOnInit() {
    
    let onRequestListsReceived = (data) => {
      this.pendingSupportRequestList = data["pendingSupportRequestList"]
      this.deviceRequestList = data["deviceRequestList"]
      this.totalDeviceInstanceList = data["totalDeviceInstanceList"]
      this.clientDeviceInstanceList = data["clientDeviceInstanceList"]
      this.freeMasterList = data["freeMasterList"]
      this.clientList = data["clientList"]
      this.clientDevices = data["clientDevices"]
      this.diagnosticsResponseList = data["diagnosticsResponseList"]
    }
    let url = this.serverDomain + this.getListsPath
    let params = {
      clientName: this.clientName,
    }


    $.ajax({
      type: "GET",
      url: url,
      data: params,
      dataType: "json",
      success: onRequestListsReceived,
    });
  }

  assignSupportRequest() {
    let url = this.serverDomain + this.assignSupportRequestPath
    let data = {
      master: this.selectedMaster,
      supportRequest: this.selectedPendingSupportRequest,
    }

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

  assignDiagnosticsRequest() {
    let url = this.serverDomain + this.assignDiagnosticsRequestPath
    let data = {
      diagnosticsRequestName: this.newDiagnosticsRequestName,
      master: this.selectedMaster,
      deviceInstance: this.selectedDeviceInstanceFromStorage,
    }

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

  closeDiagnosticsRequest() {
    let url = this.serverDomain + this.closeDiagnosticsRequestPath
    let data = this.selectedDiagnosticsResponse

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

  assignDeviceToClient() {
    let url = this.serverDomain + this.assignDeviceToClientPath
      let data = {
        deviceInstance: this.selectedDeviceInstanceFromStorage,
        client: this.selectedClient,
      }
  
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

  unassignDeviceFromClient() {
    let url = this.serverDomain + this.unassignDeviceFromClientPath
      let data = this.selectedClientDevice
      alert(JSON.stringify(this.selectedClientDevice))
  
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

  onClientSelected() {
    this.selectedClientDeviceList = this.clientDevices[this.selectedClient.id]
    // alert(JSON.stringify(this.selectedClient))
  }

}
