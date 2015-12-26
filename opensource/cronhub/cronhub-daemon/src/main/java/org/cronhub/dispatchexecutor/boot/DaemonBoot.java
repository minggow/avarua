package org.cronhub.dispatchexecutor.boot;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;

import org.cronhub.dispatchexecutor.bean.ParamCommons;
import org.cronhub.dispatchexecutor.caller.gen.ExecutorService;
import org.cronhub.dispatchexecutor.caller.impl.ExecutorServiceImpl;
import org.cronhub.dispatchexecutor.utils.ProcessUtils;


public class DaemonBoot implements Daemon{
	TServer server;
	private void startServer(int port) {
		try {
			TServerSocket serverTransport = new TServerSocket(port);
			ExecutorService.Processor processor = new ExecutorService.Processor(new ExecutorServiceImpl());
			Factory protFactory = new TBinaryProtocol.Factory(true, true);
			TThreadPoolServer.Args arg = new TThreadPoolServer.Args(serverTransport);
			arg.protocolFactory(protFactory);
			arg.processor(processor);
			server = new TThreadPoolServer(arg);
			
			
			System.out.println("Starting server pid:"+ProcessUtils.getPid()+" on port "+port+" ...");
			server.serve();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void stopServer(){
		if(this.server!=null && this.server.isServing()){
			this.server.stop();
		}
	}
	@Override
	public void destroy() {
		stopServer();
		
	}
	@Override
	public void init(DaemonContext context) throws DaemonInitException, Exception {
		String[] args = context.getArguments();
		Options options = new Options();
		options.addOption("p","port",true,"start port");
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = parser.parse(options, args);
		ParamCommons.SERVICE_PORT =Integer.parseInt(cmd.getOptionValue("port").toString());
	}
	@Override
	public void start() throws Exception {
		startServer(ParamCommons.SERVICE_PORT);
	}
	@Override
	public void stop() throws Exception {
		stopServer();
		
	}
}
