import { CommonModule } from '@angular/common';
import { Component, WritableSignal, signal } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatIconModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  readonly logoUrl : string = './../../../../assets/images/logo.png';

  // States
  isDarkMode : WritableSignal<boolean> = signal(false);

  changeTheme() : void {
    this.isDarkMode.set(!this.isDarkMode());
  }

}
