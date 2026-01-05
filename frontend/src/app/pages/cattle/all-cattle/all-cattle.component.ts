import { Component, OnInit, inject } from '@angular/core';
import { IAllCattle } from '../../../models/cattle.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { LoadingComponent } from '../../../loading/loading.component';
import { NgClass } from '@angular/common';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { CattleFormComponent } from '../cattle-form/cattle-form.component';
import { DropdownDirective } from '../../../shared/directives/dropdown.directive';
import { ConfirmationModalComponent } from '../../../shared/confirmation-modal/confirmation-modal.component';

@Component({
  selector: 'app-all-cattle',
  standalone: true,
  imports: [
    LoadingComponent,
    NgClass,
    ModalComponent,
    CattleFormComponent,
    DropdownDirective,
    ConfirmationModalComponent
  ],
  templateUrl: './all-cattle.component.html',
  styleUrl: './all-cattle.component.css'
})
export class AllCattleComponent implements OnInit {

  loading: boolean = true;
  listCattles: IAllCattle[] = [];

  // Modal State
  isModalOpen = false;
  modalTitle = 'Añadir nuevo animal';
  selectedCattleId: number | null = null;

  // Confirmation Modal State
  isConfirmModalOpen = false;
  cattleToDeleteId: number | null = null;

  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerCattle();
  }

  obtenerCattle() {
    this._apiService.getAllCattles().subscribe((data: IAllCattle[]) => {
      this.listCattles = data;
      this.loading = false;
    });
  }

  navegate(id: number): void {
    this._router.navigate(['cattle', id])
  }

  deleteCattle(id: number): void {
    this.cattleToDeleteId = id;
    this.isConfirmModalOpen = true;
  }

  confirmDelete(): void {
    if (this.cattleToDeleteId) {
      this._apiService.deleteCattle(this.cattleToDeleteId).subscribe(() => {
        this.obtenerCattle();
        this.closeConfirmModal();
      });
    }
  }

  closeConfirmModal(): void {
    this.isConfirmModalOpen = false;
    this.cattleToDeleteId = null;
  }

  openModal(id?: number): void {
    this.isModalOpen = true;
    if (id) {
      this.selectedCattleId = id;
      this.modalTitle = 'Actualizar animal';
    } else {
      this.selectedCattleId = null;
      this.modalTitle = 'Añadir nuevo animal';
    }
  }

  closeModal(): void {
    this.isModalOpen = false;
    this.selectedCattleId = null;
  }

  handleSave() {
    this.closeModal();
    this.obtenerCattle();
  }
}