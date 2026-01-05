import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-modal',
    standalone: true,
    imports: [CommonModule],
    template: `
    <div class="modal-backdrop fade show" *ngIf="isOpen" (click)="closeModal()"></div>
    <div class="modal fade show d-block" *ngIf="isOpen" tabindex="-1" role="dialog" aria-modal="true">
      <div class="modal-dialog modal-dialog-centered" role="document" (click)="$event.stopPropagation()">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ title }}</h5>
            <button type="button" class="btn-close btn-close-white" aria-label="Close" (click)="closeModal()"></button>
          </div>
          <div class="modal-body">
            <ng-content></ng-content>
          </div>
          <div class="modal-footer" *ngIf="showFooter">
            <ng-content select="[footer]"></ng-content>
          </div>
        </div>
      </div>
    </div>
  `,
    styles: [`
    .modal-backdrop {
      background-color: rgba(0, 0, 0, 0.5);
      z-index: 1050;
    }
    .modal {
      z-index: 1055;
      overflow-y: auto;
    }
    .modal-dialog {
        transition: transform 0.3s ease-out;
    }
    .btn-close-white {
        filter: invert(1) grayscale(100%) brightness(200%);
    }
  `]
})
export class ModalComponent {
    @Input() isOpen = false;
    @Input() title = '';
    @Input() showFooter = false;
    @Output() close = new EventEmitter<void>();

    closeModal() {
        this.close.emit();
    }
}
