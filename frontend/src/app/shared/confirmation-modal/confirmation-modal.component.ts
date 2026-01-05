import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from '../modal/modal.component';

@Component({
    selector: 'app-confirmation-modal',
    standalone: true,
    imports: [CommonModule, ModalComponent],
    template: `
    <app-modal [isOpen]="isOpen" [title]="title" (close)="onCancel()">
      <div class="d-flex flex-column align-items-center text-center p-3">
        <span class="material-symbols-rounded fs-1 mb-3" 
              [ngClass]="type === 'danger' ? 'text-danger' : 'text-warning'">
          {{ type === 'danger' ? 'error' : 'warning' }}
        </span>
        <p class="fs-5 mb-1">{{ message }}</p>
        <p class="text-muted small mb-0" *ngIf="subMessage">{{ subMessage }}</p>
      </div>

      <div class="d-flex justify-content-end gap-2 mt-4">
        <button type="button" class="btn btn-outline-secondary" (click)="onCancel()">
          {{ cancelText }}
        </button>
        <button type="button" class="btn" 
                [ngClass]="type === 'danger' ? 'btn-danger' : 'btn-warning'"
                (click)="onConfirm()">
          {{ confirmText }}
        </button>
      </div>
    </app-modal>
  `
})
export class ConfirmationModalComponent {
    @Input() isOpen = false;
    @Input() title = 'Confirmación';
    @Input() message = '¿Estás seguro de realizar esta acción?';
    @Input() subMessage = '';
    @Input() confirmText = 'Confirmar';
    @Input() cancelText = 'Cancelar';
    @Input() type: 'danger' | 'warning' = 'danger';

    @Output() confirm = new EventEmitter<void>();
    @Output() cancel = new EventEmitter<void>();

    onConfirm() {
        this.confirm.emit();
    }

    onCancel() {
        this.cancel.emit();
    }
}
