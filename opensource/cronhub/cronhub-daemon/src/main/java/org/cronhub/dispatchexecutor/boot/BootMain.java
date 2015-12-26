package org.cronhub.dispatchexecutor.boot;

import org.apache.commons.cli.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TBinaryProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.cronhub.dispatchexecutor.bean.ParamCommons;
import org.cronhub.dispatchexecutor.caller.gen.ExecutorService;
import org.cronhub.dispatchexecutor.caller.impl.ExecutorServiceImpl;
import org.cronhub.dispatchexecutor.utils.ProcessUtils;

public class BootMain {
    TServer server;

    private void startServer(int port) {
        try {
            TServerSocket serverTransport = new TServerSocket(port);
            ExecutorService.Processor processor =
                    new ExecutorService.Processor<ExecutorService.Iface>(new ExecutorServiceImpl());
            Factory protFactory = new TBinaryProtocol.Factory(true, true);
            TThreadPoolServer.Args arg = new TThreadPoolServer.Args(serverTransport);
            arg.protocolFactory(protFactory);
            arg.processor(processor);
            server = new TThreadPoolServer(arg);
            System.out.println("Starting server pid:" + ProcessUtils.getPid() + " on port " + port + " ...");
            server.serve();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("p", "port", true, "start port");
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);
        ParamCommons.SERVICE_PORT = Integer.parseInt(cmd.getOptionValue("port").toString());
        BootMain main = new BootMain();
        main.startServer(ParamCommons.SERVICE_PORT);
    }
}
