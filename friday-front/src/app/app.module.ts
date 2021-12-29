import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { HttpClientModule} from '@angular/common/http';

import { FullCalendarModule } from '@fullcalendar/angular';
import  dayGridPlugin  from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import googleCalendarPlugin from '@fullcalendar/google-calendar';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MainComponent } from './main/main.component';
import { HeaderComponent } from './header/header.component';
import { CalendrierComponent } from './main/calendrier/calendrier.component';
import { FooterComponent } from './footer/footer.component';
import { DetailsComponent } from './main/details/details.component';
import { ListRdvComponent } from './main/details/list-rdv/list-rdv.component';
import { FicheRdvComponent } from './main/details/fiche-rdv/fiche-rdv.component';

FullCalendarModule.registerPlugins([
  dayGridPlugin,
  interactionPlugin,
  googleCalendarPlugin
])

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HeaderComponent,
    CalendrierComponent,
    FooterComponent,
    DetailsComponent,
    ListRdvComponent,
    FicheRdvComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    FullCalendarModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
