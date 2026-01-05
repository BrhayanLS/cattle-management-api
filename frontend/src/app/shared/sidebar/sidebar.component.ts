import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  template: `
    <div class="sidebar d-flex flex-column p-3" [class.collapsed]="collapsed">
      <a href="/" class="d-flex align-items-center mb-3 mb-md-0 text-white text-decoration-none brand-logo"
         [class.justify-content-center]="collapsed">
        <span class="material-symbols-rounded fs-3 logo-icon">agriculture</span>
        <span class="fs-4 fw-bold ms-2 logo-text" *ngIf="!collapsed">AdGan</span>
      </a>
      <hr>
      <ul class="nav nav-pills flex-column mb-auto">
        <!-- Dashboard -->
        <li class="nav-item">
          <a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact: true}" 
             class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Dashboard' : ''">
            <span class="material-symbols-rounded">dashboard</span>
            <span *ngIf="!collapsed">Dashboard</span>
          </a>
        </li>
        
        <!-- Ganaderos Section -->
        <li class="nav-header text-uppercase mt-3 mb-1" *ngIf="!collapsed">
          <small>Ganaderos</small>
        </li>
        <li *ngIf="collapsed">
            <hr class="my-2 border-secondary opacity-25">
        </li>

        <li>
          <a routerLink="/allOwners" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Ver Ganaderos' : ''">
            <span class="material-symbols-rounded">group</span>
            <span *ngIf="!collapsed">Todos</span>
          </a>
        </li>
        <li>
          <a routerLink="/owners" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Gestionar Ganaderos' : ''">
            <span class="material-symbols-rounded">person_add</span>
            <span *ngIf="!collapsed">Gestionar</span>
          </a>
        </li>

        <!-- Animales Section -->
        <li class="nav-header text-uppercase mt-3 mb-1" *ngIf="!collapsed">
          <small>Ganado</small>
        </li>
        <li *ngIf="collapsed">
            <hr class="my-2 border-secondary opacity-25">
        </li>

        <li>
          <a routerLink="/allCattles" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Inventario Total' : ''">
            <span class="material-symbols-rounded">grass</span>
            <span *ngIf="!collapsed">Inventario Total</span>
          </a>
        </li>
         <li>
          <a routerLink="/resume" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Resumen' : ''">
            <span class="material-symbols-rounded">analytics</span>
            <span *ngIf="!collapsed">Resumen</span>
          </a>
        </li>
        <li>
          <a routerLink="/cattles" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Gestión' : ''">
            <span class="material-symbols-rounded">pets</span>
            <span *ngIf="!collapsed">Gestión</span>
          </a>
        </li>
        <li>
          <a routerLink="/sold" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Vendidos' : ''">
            <span class="material-symbols-rounded">sell</span>
            <span *ngIf="!collapsed">Vendidos</span>
          </a>
        </li>
         <li>
          <a routerLink="/notAvailable" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'No Disponibles' : ''">
            <span class="material-symbols-rounded">block</span>
            <span *ngIf="!collapsed">No Disponibles</span>
          </a>
        </li>

        <!-- Ventas Section -->
        <li class="nav-header text-uppercase mt-3 mb-1" *ngIf="!collapsed">
          <small>Ventas</small>
        </li>
        <li *ngIf="collapsed">
            <hr class="my-2 border-secondary opacity-25">
        </li>

        <li>
          <a routerLink="/allSales" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Historial Ventas' : ''">
            <span class="material-symbols-rounded">receipt_long</span>
            <span *ngIf="!collapsed">Historial</span>
          </a>
        </li>
        <li>
          <a routerLink="/sales" routerLinkActive="active" class="nav-link text-white d-flex align-items-center gap-2"
             [title]="collapsed ? 'Nueva Venta' : ''">
            <span class="material-symbols-rounded">point_of_sale</span>
            <span *ngIf="!collapsed">Nueva Venta</span>
          </a>
        </li>
      </ul>
      
    </div>
  `,
  styles: [`
    .sidebar {
      width: 260px;
      height: 100vh;
      position: fixed;
      top: 0;
      left: 0;
      background-color: var(--color-brand-primary);
      color: var(--color-surface);
      z-index: 1000;
      transition: width 0.3s ease-in-out;
      overflow-y: auto;
      overflow-x: hidden;
    }

    .sidebar.collapsed {
        width: 80px;
        padding: 1rem 0.5rem !important;
    }
    
    .sidebar.collapsed .nav-link {
        justify-content: center;
        padding: 0.75rem;
    }

    .brand-logo {
        color: white !important;
        white-space: nowrap;
        overflow: hidden;
    }

    .nav-link {
      color: rgba(255, 255, 255, 0.8) !important;
      font-weight: 500;
      padding: 0.75rem 1rem;
      border-radius: 0.5rem;
      transition: all 0.2s ease;
      white-space: nowrap;
    }

    .nav-link:hover {
      background-color: rgba(255, 255, 255, 0.1);
      color: white !important;
    }

    .nav-link.active {
      background-color: var(--color-brand-secondary) !important;
      color: white !important;
      box-shadow: var(--shadow-sm);
    }

    .nav-header {
        font-size: 0.75rem;
        color: rgba(255, 255, 255, 0.5);
        padding-left: 1rem;
        letter-spacing: 0.05em;
        white-space: nowrap;
    }
  `]
})
export class SidebarComponent {
  @Input() collapsed = false;
}
