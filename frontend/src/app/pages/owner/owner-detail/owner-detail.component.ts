import { Component, OnInit, inject } from '@angular/core';
import { IAllOwner } from '../../../models/owner.model';
import { ApiService } from '../../../services/api.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { LoadingComponent } from '../../../loading/loading.component';
import { switchMap } from 'rxjs/operators';
import { DatePipe, NgClass } from '@angular/common';

@Component({
  selector: 'app-owner-detail',
  standalone: true,
  imports: [LoadingComponent, RouterLink, DatePipe, NgClass],
  templateUrl: './owner-detail.component.html',
  styleUrl: './owner-detail.component.css'
})
export class OwnerDetailComponent implements OnInit {

  loading: boolean = true;
  owner?: IAllOwner;

  private _apiService = inject(ApiService);
  private _route = inject(ActivatedRoute);
  private _router = inject(Router);

  ngOnInit(): void {
    this._route.params.pipe(
      switchMap(params => {
        const id = params['id'];
        return this._apiService.getOwner(id);
      })
    ).subscribe({
      next: (data: IAllOwner) => {
        this.owner = data;
        this.loading = false;
      },
      error: () => {
        this.loading = false;
        // Optional: Navigate back or show error
        this._router.navigate(['/owners']);
      }
    });
  }
}
