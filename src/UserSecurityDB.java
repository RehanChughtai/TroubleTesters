/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator

*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserSecurityDB 
{
    private String _lastError;
    private String _dbFilePath = "userdb.csv";
    protected String _separator = ",";
    private Map<String, String> _database = new HashMap<String, String>();
    
    /**
     * Get the value of _lastError
     *
     * @return the value of _lastError
     */
    public String getLastError() 
    {
        return _lastError;
    }

    public UserSecurityDB()
    {
    }
    
    public boolean Load()
    {
        boolean succeeded = true;

        BufferedReader reader = null;
        
        try
        {
            _database.clear();
            
            String line = "";
            
            reader = new BufferedReader(new FileReader(_dbFilePath));
            
            while ((line = reader.readLine()) != null) 
            {
                // ignore comments
                if (line.trim().startsWith("#")) continue;
                
                // ignore blank lines
                if (line.trim().length() == 0) continue;
                
                String[] tokens = line.split(_separator);
                
                if (tokens.length != 2) continue;
                
                if (!_database.containsKey(tokens[0]))
                {
                    _database.put(tokens[0], tokens[1]);
                }
            }
        }
        catch(Exception ex)
        {
            succeeded = false;
            
            _lastError = ex.getMessage();
        }
        finally
        {
            if (null != reader) 
            {
                try 
                {
                    reader.close();
                } 
                catch (Exception e) 
                {
                    _lastError = e.getMessage();
                }
            }            
        }

        return succeeded;
    }
    
    public boolean Save()
    {
        boolean succeeded = true;

        PrintWriter writer = null;
        
        try
        {
            StringBuilder sb = new StringBuilder();
            
            for (String key : _database.keySet())
            {
                sb.append(key);
                sb.append(_separator);
                sb.append(_database.get(key));
                sb.append("\r\n");
            }
            
            writer = new PrintWriter(_dbFilePath);
            writer.println(sb.toString());
        }
        catch(Exception ex)
        {
            succeeded = false;
            
            _lastError = ex.getMessage();
        }
        finally
        {
            if (null != writer) 
            {
                try 
                {
                    writer.close();
                } 
                catch (Exception e) 
                {
                    _lastError = e.getMessage();
                }
            }            
                
        }
        return succeeded;
    }
    
    public boolean Load(String dbFilePath)
    {
        _dbFilePath = dbFilePath;

        return Load();
    }
    public boolean Authenticate(String username, String password)
    {
        boolean succeeded = false;
        
        if (_database.containsKey(username))
        {
            String dbPassword = _database.get(username);
            
            succeeded = (dbPassword.equals(password));    
        }
       
        return succeeded;
    }
    
    public void AddUser(String username, String password) throws Exception
    {
        if (_database.containsKey(username))
        {
            throw new Exception("Username already exists");
        }
        
        _database.put(username, password);
    }
    
    public void DeleteUser(String username)
    {
        if (_database.containsKey(username))
        {
            _database.remove(username);
        }
    }
    
    public void SetPassword(String username, String newPasssword) throws Exception
    {
        if (!_database.containsKey(username))
        {
            throw new Exception("Username not found");
        }
        
        _database.put(username, newPasssword);
    }
}
