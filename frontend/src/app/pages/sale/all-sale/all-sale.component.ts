import { Component, OnInit, inject } from '@angular/core';
import { LoadingComponent } from '../../../loading/loading.component';
import { IAllSale, ISale } from '../../../models/sale.model';
import { ApiService } from '../../../services/api.service';
import { Router } from '@angular/router';
import { CurrencyPipe, DatePipe, NgClass } from '@angular/common';
import { ModalComponent } from '../../../shared/modal/modal.component';
import { SaleFormComponent } from '../sale-form/sale-form.component';

@Component({
  selector: 'app-all-sale',
  standalone: true,
  imports: [LoadingComponent, CurrencyPipe, DatePipe, NgClass, ModalComponent, SaleFormComponent],
  templateUrl: './all-sale.component.html',
  styleUrl: './all-sale.component.css'
})
export class AllSaleComponent implements OnInit {

  loading: boolean = true;
  listSale: IAllSale[] = [];

  // Modal Control
  isModalOpen = false;
  modalTitle = 'Registrar Venta';
  selectedSale: ISale | undefined = undefined;

  private _apiService = inject(ApiService);
  private _router = inject(Router);

  ngOnInit(): void {
    this.obtenerSales();
  }

  obtenerSales() {
    this._apiService.getAllSales().subscribe((data: IAllSale[]) => {
      this.listSale = data;
      this.loading = false;
    });
  }

  navegate(id: number): void {
    this._router.navigate(['sale', id]);
  }

  deleteSale(id: number): void {
    if (confirm('¿Estás seguro de eliminar esta venta?')) {
      this._apiService.deleteSale(id).subscribe(() => {
        this.obtenerSales();
      });
    }
  }

  // --- Modal Logic ---

  openAddModal() {
    this.selectedSale = undefined;
    this.modalTitle = 'Registrar Nueva Venta';
    this.isModalOpen = true;
  }

  openEditModal(sale: IAllSale) {
    // Map IAllSale (View Model) to ISale (Form Model) if structure differs significantly
    // Based on interfaces, IAllSale has 'saleCattles: SaleCattle[]' while ISale has 'saleCattles: SaveSaleCattle[]'
    // I need to map them properly.

    const mappedSale: ISale = {
      idSale: sale.idSale,
      fechaVenta: sale.fechaVenta,
      precioKilo: sale.precioKilo,
      valorCamion: sale.valorCamion,
      valorBascula: sale.valorBascula,
      saleCattles: sale.saleCattles.map(sc => ({
        cattleId: sc.cattleId,
        peso: sc.peso
      }))
    };

    this.selectedSale = mappedSale;
    this.modalTitle = 'Actualizar Venta';
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
    this.selectedSale = undefined;
  }

  handleFormSave() {
    this.closeModal();
    this.obtenerSales();
  }
}
