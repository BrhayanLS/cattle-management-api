import { Component, OnInit, inject } from '@angular/core';
import { LoadingComponent } from '../../../loading/loading.component';
import { IAllOwner, IOwner } from '../../../models/owner.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { NgClass } from '@angular/common';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { OwnerFormComponent } from '../owner-form/owner-form.component';

@Component({
  selector: 'app-all-owner',
  standalone: true,
  imports: [LoadingComponent, NgClass, ModalComponent, OwnerFormComponent],
  templateUrl: './all-owner.component.html',
  styleUrl: './all-owner.component.css'
})
export class AllOwnerComponent implements OnInit {

  loading: boolean = true;
  listOwners: IAllOwner[] = [];

  // Modal Control
  isModalOpen = false;
  modalTitle = 'Actualizar Ganadero';
  selectedOwner: IOwner | undefined = undefined;

  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerOwner();
  }

  obtenerOwner() {
    this._apiService.getAllOwners().subscribe((data: IAllOwner[]) => {
      this.listOwners = data;
      this.loading = false;
    })
  }

  navegate(id: number): void {
    this._router.navigate(['owner', id]);
  }

  deleteOwner(id: number): void {
    if (confirm('¿Estás seguro del eliminar a este dueño?')) {
      this._apiService.deleteOwner(id).subscribe(() => {
        this.obtenerOwner();
      });
    }
  }

  openEditModal(owner: IAllOwner) {
    this.selectedOwner = {
      ...owner,
      roleId: owner.role.id,
      password: '',
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
    this.obtenerOwner();
  }
}