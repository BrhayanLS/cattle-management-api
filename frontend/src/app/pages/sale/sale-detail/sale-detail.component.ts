import { Component, OnInit, inject } from '@angular/core';
import { LoadingComponent } from '../../../loading/loading.component';
import { IAllSale } from '../../../models/sale.model';
import { ApiService } from '../../../services/api.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CurrencyPipe, DatePipe, NgClass } from '@angular/common';
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-sale-detail',
  standalone: true,
  imports: [LoadingComponent, CurrencyPipe, DatePipe, RouterLink, NgClass],
  templateUrl: './sale-detail.component.html',
  styleUrl: './sale-detail.component.css'
})
export class SaleDetailComponent implements OnInit {

  loading: boolean = true;
  sale?: IAllSale;

  private _apiService = inject(ApiService);
  private _route = inject(ActivatedRoute);

  ngOnInit(): void {
    this._route.params.pipe(
      switchMap(params => this._apiService.getSale(params['id']))
    ).subscribe((data: IAllSale) => {
      this.sale = data;
      this.loading = false;
    });
  }
}
