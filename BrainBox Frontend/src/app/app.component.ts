import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Observable, map, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Response } from '../models/response';
import { Event } from '../models/event';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'brainbox';
  events$ !: Observable<Event[]>;
  private readonly baseUrl = 'http://localhost:8080/api/v1.0/events';

  private _httpClient : HttpClient = inject(HttpClient);

  ngOnInit(){
    this.events$ = this.getAllEvents();
  }

  getAllEvents() : Observable<Event[]> {
    return this._httpClient.get<Event[]>(this.baseUrl).pipe(
      tap(console.log),
      map( (_res : Response<Event[]>) => _res.resource)
    )
  }
}
