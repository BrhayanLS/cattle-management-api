import { Directive, ElementRef, HostBinding, HostListener } from '@angular/core';

@Directive({
    selector: '[appDropdown]',
    standalone: true,
    exportAs: 'appDropdown'
})
export class DropdownDirective {
    private _isOpen = false;

    constructor(private elRef: ElementRef) { }

    @HostBinding('class.show') get isOpen() {
        return this._isOpen;
    }

    toggle() {
        this._isOpen = !this._isOpen;
    }

    @HostListener('document:click', ['$event']) toggleOpen(event: Event) {
        const isInside = this.elRef.nativeElement.contains(event.target);
        if (!isInside) {
            this._isOpen = false;
        }
    }
}
