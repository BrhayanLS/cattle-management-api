import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [CommonModule],
    template: `
    <header class="navbar navbar-expand-lg bg-white border-bottom shadow-sm px-3 py-2">
      <div class="container-fluid p-0">
        
        <!-- Toggle Button -->
        <button class="btn btn-outline-light text-dark border-0 me-3" (click)="toggleSidebar()">
          <span class="material-symbols-rounded">menu</span>
        </button>

        <!-- Page Title (Optional placeholder) -->
        <h5 class="m-0 text-muted d-none d-md-block">Bienvenido, Admin</h5>

        <!-- User Profile (Right Side) -->
        <div class="ms-auto d-flex align-items-center gap-3">
          <div class="d-flex align-items-center gap-2 user-profile">
            <div class="text-end d-none d-md-block">
               <span class="d-block fw-bold text-dark small">Admin User</span>
               <small class="text-muted d-block" style="font-size: 0.75rem;">Administrador</small>
            </div>
            <div class="avatar bg-success rounded-circle d-flex align-items-center justify-content-center text-white" style="width: 38px; height: 38px;">
                <span class="material-symbols-rounded">person</span>
            </div>
          </div>
        </div>

      </div>
    </header>
  `,
    styles: [`
    :host {
      display: block;
      width: 100%;
      z-index: 1000;
    }
  `]
})
export class HeaderComponent {
    @Output() onToggleSidebar = new EventEmitter<void>();

    toggleSidebar() {
        this.onToggleSidebar.emit();
    }
}
