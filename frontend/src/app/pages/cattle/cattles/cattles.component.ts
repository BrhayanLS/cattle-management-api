import { Component, OnInit, inject } from '@angular/core';
import { IAllCattle } from '../../../models/cattle.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { LoadingComponent } from '../../../loading/loading.component';
import { NgClass } from '@angular/common';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { CattleFormComponent } from '../cattle-form/cattle-form.component';

@Component({
  selector: 'app-cattles',
  standalone: true,
  imports: [LoadingComponent, NgClass, ModalComponent, CattleFormComponent],
  templateUrl: './cattles.component.html',
  styleUrl: './cattles.component.css'
})
export class CattlesComponent implements OnInit {

  loading: boolean = true;
  listCattles: IAllCattle[] = [];

  // Modal State
  isModalOpen = false;
  modalTitle = 'Añadir nuevo animal';

  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerCattle();
  }

  obtenerCattle() {
    this._apiService.getCattles().subscribe((data: IAllCattle[]) => {
      this.listCattles = data;
      this.loading = false;
    });
  }

  navegate(id: number): void {
    this._router.navigate(['cattle', id])
  }

  openModal(): void {
    this.isModalOpen = true;
    this.modalTitle = 'Añadir nuevo animal';
  }

  closeModal(): void {
    this.isModalOpen = false;
  }

  handleSave() {
    this.closeModal();
    this.obtenerCattle();
  }
}
