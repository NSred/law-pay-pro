import {Directive, ElementRef, HostListener} from '@angular/core';

@Directive({
  selector: '[appExpiryDate]',
  standalone: true
})
export class ExpiryDateDirective {

  constructor(private el: ElementRef<HTMLInputElement>) {}

  @HostListener('input', ['$event']) onInputChange(event: Event) {
    const input = this.el.nativeElement;
    let value = input.value.replace(/[^0-9]/g, ''); // Only allow numbers

    // Formatting for month
    if (value.length == 1) {
      if (value > '1') {
        value = '0' + value + ' / '; // Auto-complete for months greater than 1
      }
    } else if (value.length == 2) {
      if (value == '00') { // Prevent '00' as a month
        value = value.substring(0, 1);
      } else if (value[0] === '1' && value[1] > '2') { // Prevent invalid months like '14', '19', etc.
        value = value.substring(0, 1);
      } else {
        value += ' / '; // Add slash after valid two-digit month
      }
    } else if (value.length > 2) {
      // Add slash after month and allow year input
      value = value.substring(0, 2) + ' / ' + value.substring(2);
    }

    // Restrict total length to MM / YYYY format (max 9 characters: 2 digits, 1 space, 1 slash, 4 digits)
    if (value.length > 9) {
      value = value.substring(0, 9);
    }

    input.value = value; // Update the input field
    event.stopPropagation();
  }
  // @HostListener('input', ['$event']) onInputChange(event: Event) {
  //   const input = this.el.nativeElement;
  //   let value = input.value.replace(/[^0-9]/g, ''); // Only allow numbers
  //
  //   // Formatting for month
  //   if (value.length > 0) {
  //     if (value.length === 1 && parseInt(value) > 1) {
  //       value = '0' + value;
  //     } else if (value.length > 2) {
  //       value = value.substring(0, 2) + ' / ' + value.substring(2);
  //     }
  //
  //     // Restrict to MM / YY or MM / YYYY format
  //     if (value.length > 7) {
  //       value = value.substring(0, 7);
  //     }
  //   }
  //
  //   input.value = value; // Update the input field
  //   event.stopPropagation();
  // }
}
