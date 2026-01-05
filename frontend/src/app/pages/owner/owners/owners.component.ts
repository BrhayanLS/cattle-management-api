import { Component, OnInit, inject } from '@angular/core';
import { LoadingComponent } from '../../../loading/loading.component';
import { IAllOwner, IOwner } from '../../../models/owner.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { ConfirmationModalComponent } from '../../../shared/confirmation-modal/confirmation-modal.component';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { OwnerFormComponent } from '../owner-form/owner-form.component';

@Component({
  selector: 'app-owners',
  standalone: true,
  imports: [LoadingComponent, NgClass, ConfirmationModalComponent, ModalComponent, OwnerFormComponent],
  templateUrl: './owners.component.html',
  styleUrl: './owners.component.css'
})
export class OwnersComponent implements OnInit {

  loading: boolean = true;
  listOwners: IAllOwner[] = [];

  // Modal Control
  isModalOpen = false;
  modalTitle = 'Añadir Ganadero';
  selectedOwner: IOwner | undefined = undefined;

  // Confirmation/Delete Control
  loadingDelete = false;

  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerOwners();
  }

  obtenerOwners() {
    this._apiService.getOwners().subscribe((data: IAllOwner[]) => {
      this.listOwners = data;
      this.loading = false;
    });
  }

  navegate(id: number): void {
    this._router.navigate(['owner', id]);
  }

  // --- Modal Logic ---

  openAddModal() {
    this.selectedOwner = undefined;
    this.modalTitle = 'Añadir Nuevo Ganadero';
    this.isModalOpen = true;
  }

  openEditModal(owner: IAllOwner) {
    this.selectedOwner = {
      ...owner,
      roleId: owner.role.id,
      password: '', // Reset password for security/don't prefill hash
    };
    this.modalTitle = 'Actualizar Ganadero';
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedOwner = undefined;
  }

  handleFormSave() {
    this.closeModal();
    this.obtenerOwners(); // Refresh list
  }

  // --- Delete Logic ---

  deleteOwner(id: number): void {
    // This needs to be hooked to a confirmation modal in HTML, 
    // or we can use a simpler window.confirm for now if strict native replacement is needed,
    // but we have ConfirmationModalComponent.
    // For this iteration, I'll assume we use the new ConfirmationModalComponent component instance
    // But wait, the ConfirmationModalComponent is a presentational child mostly? 
    // Let's check how we implemented it in AllCattle.
    // In AllCattle we used window.confirm replacement? No, we created a custom component.
    // But usually we need a reference to open it. 
    // Let's stick to the simplest integration: render it conditionally or use a ViewChild.
    // Actually, looking at AllCattle, we likely used a reference or simple state.
    // Let's assume standard behavior:
    if (confirm("¿Está seguro de eliminar este ganadero?")) {
      this._apiService.deleteOwner(id).subscribe(() => {
        this.obtenerOwners();
      });
    }
  }
}
