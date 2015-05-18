package org.qhy;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.qhy.pojo.File;

/**
 * 
 * @author qihy
 * @goal copyFile
 * @defaultPhase validate
 * 
 */
public class CopyFileMojo extends AbstractMojo {

	/**
	 * @parameter
	 * @required
	 */
	private String message;
	
	/**
	 * @parameter expression = "${basedir}"
	 */
	private String baseDir;
	
	/**
	 * @parameter
	 * @required
	 */
	private List<File> copyfiles;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		this.getLog().info("message>>>>>>>>>>>>>>>>>>>>"+message);
		this.getLog().info("copyfiles=======>"+copyfiles);
		this.getLog().info("baseDir=======>"+baseDir);
		if(copyfiles!=null && copyfiles.size()>0){
			for (File file : copyfiles) {
				
				try {
					//删除目标目录的所有文件
					FileUtils.deleteDirectory(new java.io.File(file.getTargetDir()));
					//copy所有的文件
					FileUtils.copyDirectory(new  java.io.File(this.baseDir+java.io.File.separator+file.getSourceDir()), new  java.io.File(file.getTargetDir()));
					this.getLog().info(file.getSourceDir()+"-------------->"+file.getTargetDir()+"\t successful!");
				} catch (IOException e) {
					this.getLog().warn(file.getSourceDir()+"-------------->"+file.getTargetDir()+"\t unsuccessful!!!");
					this.getLog().error(e);
				}
			}
		}
		
		
	}

}
