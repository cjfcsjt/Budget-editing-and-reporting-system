package com.cjf.ysxt.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cjf.ysxt.BaseTest;
import com.cjf.ysxt.entity.Worker;
import com.cjf.ysxt.dao.WorkerDao;
public class WorkerDaoTest extends BaseTest{

	@Autowired
	private WorkerDao workerDao;

	@Test
	public void testQueryById() throws Exception {
		System.out.println("测试testQueryById");
		String id = "2018071501";
		Worker worker = workerDao.queryByWorkerId(id);
		System.out.println(worker);
	}
	
//	@Test
//	public void testQueryAll() throws Exception {
//		System.out.println("测试testQueryAll");
//		List<Worker> workers = workerDao.queryAll(0, 100);
//		for (Worker worker : workers) {
//			System.out.println(worker);
//		}
//	}
//	
//	@Test
//	public void testQueryByWorkerName() throws Exception {
//		System.out.println("测试testQueryByWorkerName");
//		String name = "平头哥";
//		List<Worker> workers = workerDao.queryByWorkerName(name);
//		for (Worker worker : workers) {
//			System.out.println(worker);
//		}
//	}
//	
//	@Test
//	public void testQueryByCollegeName() throws Exception {
//		System.out.println("测试testQueryByCollegeName");
//		String name = "软件学院";
//		List<Worker> workers = workerDao.queryByCollegeName(name);
//		for (Worker worker : workers) {
//			System.out.println(worker);
//		}
//	}
//	
//	@Test
//	public void testQueryByDepartmentId() throws Exception {
//		System.out.println("测试testQueryByDepartmentId");
//		int id = 3;
//		List<Worker> workers = workerDao.queryByDepartmentId(id);
//		for (Worker worker : workers) {
//			System.out.println(worker);
//		}
//	}
//	
//	@Test
//    public void testInsertWorker() throws Exception {
//		//测试时需要换�?个测试用�?
//		Worker worker = new Worker(2018071500,"周杰伦","男","1222","教导主任","温州","19970118","123456","计算机学院",20);
//        int insert = workerDao.insertWorker(worker);
//        System.out.println("insert=" + insert);
//    }
//	
//	@Test
//	public void testdeleteWorker() throws Exception {
//		int workerid = 2018071503;
//		int delete = workerDao.deleteWorker(workerid );
//		System.out.println("delete" + delete);
//	}
}
