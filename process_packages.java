import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Request {
    public Request(int arrival_time, int process_time, int requestnumber) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
        this.requestnumber = requestnumber;
    }
    public int requestnumber;
    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayList<Integer>();
    }

    public Response Process(Request request) {
        // write your code here
    	for (int i=0;i<bufferupperlimit;i++){
    		maxfinishtime = Math.max(finish_time_.get(i),maxfinishtime);
    	}//find the last finish time of all requests that go before this request
    	
    	maxfinishtime = 0;
    	bufferupperlimit = size_;
    	finish_time_.add(request.arrival_time+request.process_time);//do only if adding object to queue 
    	if (request.arrival_time>maxfinishtime){//request came in when buffer is empty; 
    		return new Response(true, finish_time_.get(request.requestnumber));//process immediately
    	}
    	else if (size_<=request.requestnumber){
    		return new Response(false, -1);
    	}
    	else if (size_>request.requestnumber){ //buffer has empty spaces
    		enqueue();
    		return new Response(true, finish_time_.get(request.requestnumber));//process immediately
    	}
    	else
    		return new Response(false, -1);
    	
    }

    private void enqueue() {
		// TODO Auto-generated method stub
		
	}

	private int size_;
    private ArrayList<Integer> finish_time_;
    Queue<Integer> queue = new LinkedList<Integer>();
    private int maxfinishtime;
    private int bufferupperlimit = 0;
}

class process_packages {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        int requestnumber = 0;
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requestnumber++;
            requests.add(new Request(arrival_time, process_time, requestnumber));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
