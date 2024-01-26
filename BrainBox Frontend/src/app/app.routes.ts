import { Routes } from '@angular/router';
import { HomeComponent } from './components/pages/home/home.component';

export const routes: Routes = [

    {
        path: 'home',
        component: HomeComponent,
        title: 'BrainBox Home'
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    },
    {
        path: '**',
        loadComponent: () =>import('./components/pages/error/error.component').then( error => error.ErrorComponent),
        title: '404 - Page Doesn\'t exist'
    }
    
];
