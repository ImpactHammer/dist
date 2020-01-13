import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { HelloComponent } from './hello.component';
import { ClientComponent } from './client/client.component';
import { ManagerComponent } from './manager/manager.component';
import { MasterComponent } from './master/master.component';

@NgModule({
  imports:      [ BrowserModule, FormsModule, HttpClientModule,
      RouterModule.forRoot([
      { path: 'client', component: ClientComponent },
      { path: 'manager', component: ManagerComponent },
      { path: 'master', component: MasterComponent },
    ])
  ],
  declarations: [ AppComponent, HelloComponent,
                  ClientComponent, ManagerComponent, MasterComponent],
  bootstrap:    [ AppComponent ]
})
export class AppModule { }
