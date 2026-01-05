import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  totalCattle: number = 0;
  totalActive: number = 0;
  totalInactive: number = 0;
  loading: boolean = true;

  private _apiService = inject(ApiService);

  ngOnInit(): void {
    this._apiService.getAllCattles().subscribe({
      next: (data) => {
        this.totalCattle = data.length;
        this.totalActive = data.filter(c => c.estado === 1).length;
        this.totalInactive = data.filter(c => c.estado !== 1).length;
        this.loading = false;
      },
      error: (err) => {
        console.error("Error loading stats", err);
        this.loading = false;
      }
    });
  }
}
