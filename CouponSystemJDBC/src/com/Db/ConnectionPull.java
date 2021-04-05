package com.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPull {
	private Stack<Connection> connections = new Stack<>();

	private static ConnectionPull instance = null;// = new ConnectionPool();

	private ConnectionPull() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("Creating connection #" + i);
			try {
				Connection conn = DriverManager.getConnection(DatabaseManager.getUrl(), DatabaseManager.getUser(),
						DatabaseManager.getPass());
				connections.push(conn);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static ConnectionPull getInstance() {
		if (instance == null) {
			synchronized (ConnectionPull.class) {
				if (instance == null) {
					instance = new ConnectionPull();
				}
			}
		}
		return instance;
	}

	public Connection getConnection() throws InterruptedException {

		synchronized (connections) {

			if (connections.isEmpty()) {
				connections.wait();
			}

			return connections.pop();
		}
	}

	public void returnConnection(Connection conn) {

		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	public void closeAllConnection() throws InterruptedException {

		synchronized (connections) {

			while (connections.size() < 10) {
				connections.wait();
			}

			for (Connection conn : connections) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public PreparedStatement prepareStatement(String queryCreateSchema) {
		// TODO Auto-generated method stub
		return null;
	}

	public void close() {
		instance.close();
		// TODO Auto-generated method stub
		
	}
}
