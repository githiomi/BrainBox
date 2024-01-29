import { Injectable, WritableSignal, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  // Signal States
  isDarkMode: WritableSignal<boolean> = signal(false);

  // Method to get current theme state
  getThemeState(): boolean {
    return this.isDarkMode();
  }

  // Method to change theme
  changeTheme(): void {
    if (this.isDarkMode())
      this.isDarkMode.set(false)
    else
      this.isDarkMode.set(true)
  }
}
