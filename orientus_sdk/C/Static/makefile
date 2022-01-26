all: packet_example

packet_example: an_packet_protocol.c an_packet_protocol.h orientus_data_dump.h orientus_packets.c orientus_packets.h packet_example.c
	gcc -pedantic -Wall -o packet_example an_packet_protocol.c orientus_packets.c packet_example.c
	
clean:
	rm packet_example
