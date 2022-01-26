#include "pico/stdlib.h"
#include "hardware/uart.h"
#include "hardware/irq.h"
#include <stdio.h>

#define UART_ID uart0
#define BAUD_RATE 115200
#define DATA_BITS 8
#define STOP_BITS 1
#define PARITY UART_PARITY_NONE

#define UART_TX_PIN 0
#define UART_RX_PIN 1

void on_uart_rx(){

}

int main(){
    stdio_init_all();
    /*
    uart_init(UART_ID, 115200);

    //uart configurations
    gpio_set_function(UART_TX_PIN, GPIO_FUNC_UART);
    gpio_set_function(UART_RX_PIN, GPIO_FUNC_UART);
    int __unused actual = uart_set_baudrate(UART_ID,BAUD_RATE);

    uart_set_hw_flow(UART_ID,false,false);
    uart_set_format(UART_ID, DATA_BITS, STOP_BITS, PARITY);
    uart_set_fifo_enabled(UART_ID, false);
    
    int UART_IRQ = UART_ID == uart0 ? UART0_IRQ : UART1_IRQ;

    irq_set_exclusive_handler(UART_IRQ, on_uart_rx);
    irq_set_enabled(UART_IRQ,true);

    uart_set_irq_enables(UART_ID, true, false);
    */
    while(1){
        sleep_ms(100);
        printf("loop test\n");
    }

}