import { Routes } from '@angular/router';
export const routes: Routes = [
    { path: '', loadComponent: () => import('./pages/home/home.component').then(m => m.HomeComponent) },
    //Cattles
    { path: 'allCattles', loadComponent: () => import('./pages/cattle/all-cattle/all-cattle.component').then(m => m.AllCattleComponent) },
    { path: 'cattle/:id', loadComponent: () => import('./pages/cattle/cattle-detail/cattle-detail.component').then(m => m.CattleDetailComponent) },
    { path: 'cattles', loadComponent: () => import('./pages/cattle/cattles/cattles.component').then(m => m.CattlesComponent) },
    { path: 'resume', loadComponent: () => import('./pages/cattle/resume/resume.component').then(m => m.ResumeComponent) },
    { path: 'sold', loadComponent: () => import('./pages/cattle/sold/sold.component').then(m => m.SoldComponent) },
    { path: 'notAvailable', loadComponent: () => import('./pages/cattle/not-available/not-available.component').then(m => m.NotAvailableComponent) },

    //Owners
    { path: 'allOwners', loadComponent: () => import('./pages/owner/all-owner/all-owner.component').then(m => m.AllOwnerComponent) },
    { path: 'owner/:id', loadComponent: () => import('./pages/owner/owner-detail/owner-detail.component').then(m => m.OwnerDetailComponent) },
    { path: 'owners', loadComponent: () => import('./pages/owner/owners/owners.component').then(m => m.OwnersComponent) },

    //Sales
    { path: 'allSales', loadComponent: () => import('./pages/sale/all-sale/all-sale.component').then(m => m.AllSaleComponent) },
    { path: 'sale/:id', loadComponent: () => import('./pages/sale/sale-detail/sale-detail.component').then(m => m.SaleDetailComponent) },
    { path: 'sales', loadComponent: () => import('./pages/sale/sales/sales.component').then(m => m.SalesComponent) }
];