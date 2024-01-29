import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Output, WritableSignal, inject, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { ThemeService } from '../../../services/theme.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, MatIconModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  readonly logoUrl: string = './../../../../assets/images/logo.png';

  // Dependency Injection
  private _themeService: ThemeService = inject(ThemeService);

  // States
  isDarkMode: boolean;

  constructor() {
    this.isDarkMode = this._themeService.getThemeState();
  }

  changeTheme(): void {
    this._themeService.changeTheme();
  }

}
