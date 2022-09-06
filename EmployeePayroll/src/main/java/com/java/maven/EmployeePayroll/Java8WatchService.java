package com.java.maven.EmployeePayroll;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;


public class Java8WatchService {
	private final WatchService watcher;
	private final Map<WatchKey,Path> dirWatchers;

	/**
	 * Creates a WatchService and registers the given directory
	 * @param dir
	 * @throws IOException
	 */
	public Java8WatchService(Path dir) throws IOException{
		this.watcher = FileSystems.getDefault().newWatchService();
		this.dirWatchers = new HashMap<WatchKey,Path>();
		scanAndRegisterDirectories(dir);
	}

	/**
	 * Register the given directory with the WatchService; This function will be called by FileVisitor
	 * @param dir
	 * @throws IOException
	 */
	private void registerDirWatchers(Path dir) throws IOException{
		WatchKey key = dir.register(watcher,ENTRY_CREATE,ENTRY_DELETE,ENTRY_MODIFY);
		dirWatchers.put(key, dir);
	}

	/**
	 * Register the given directory, and all its sub-directories, with the WatchService.
	 * @param start
	 * @throws IOException
	 */
	private void scanAndRegisterDirectories(final Path start) throws IOException {
		Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
		@Override
		public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs) throws IOException{
			registerDirWatchers(dir);
			return FileVisitResult.CONTINUE;
		}
		});
	}
	/**
	 * Context for directory entry event is the file name of entry
	 */

	@SuppressWarnings({"rawtypes","unchecked"})
	/**
	 * Process all events for keys queued to the watcher
	 */
	public void processEvents() {
		while(true) {
			WatchKey key;
			try {
				key = watcher.take();
			}catch(InterruptedException x) {
				return;
			}
			Path dir = dirWatchers.get(key);
			if(dir==null)
				continue;
			for(WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				Path name = ((WatchEvent<Path>)event).context();
				Path child = dir.resolve(name);
				System.out.format("%s : %s\n", event.kind().name(),child);

				/**
				 * if directory is created, and watching recursively, then register it and its sub-directories
				 */
				if(kind == ENTRY_CREATE) {
					try {
						if(Files.isDirectory(child))
							scanAndRegisterDirectories(child);
					}catch (IOException x) {}
				}else if(kind.equals(ENTRY_DELETE)) {
					if(Files.isDirectory(child))
						dirWatchers.remove(key);
				}

			}
			/**
			 * reset key and remove from set if directory no longer accessible
			 */
			boolean valid = key.reset();
			if(!valid) {
				dirWatchers.remove(key);
				/**
				 * reset key and remove from set if directory no longer accessible
				 */
				if(dirWatchers.isEmpty())
					break;
			}
		}
	}
}