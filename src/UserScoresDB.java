
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class UserScoresDB 
{
    private String _lastError;
    private String _dbFilePath = "userscores.csv";
    protected String _separator = ",";
    private Map<String, UserScore> _scores = new HashMap<String, UserScore>();
    private Map<Integer, String> _positions = new TreeMap<Integer, String>();
    
    public Map<Integer, String> getPositions()
    {
        return _positions;
    }
    
    public String getLastError() 
    {
        return _lastError;
    }

    public UserScoresDB()
    {
    }
    
    public boolean Load()
    {
        boolean succeeded = true;

        BufferedReader reader = null;
        
        try
        {
            String line = "";
            
            reader = new BufferedReader(new FileReader(_dbFilePath));
            
            while ((line = reader.readLine()) != null) 
            {
                // ignore comments
                if (line.trim().startsWith("#")) continue;
                
                // ignore blank lines
                if (line.trim().length() == 0) continue;
                
                String[] tokens = line.split(_separator);
                
                if (tokens.length != 4) continue;
                
                String username = tokens[0];
                String lastGameTimestamp = tokens[1];
                int classicScore = Integer.parseInt(tokens[2]);
                int arcadeScore = Integer.parseInt(tokens[3]);
                                
                if (!_scores.containsKey(username))
                {
                    UserScore score = new UserScore(username, lastGameTimestamp, classicScore, arcadeScore);
                    _scores.put(username, score);
                    
                    if (score.getScoreTotal() > 0)
                    {
                        _positions.put(score.getScoreTotal(), score.getUsername());
                    }
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
            
            for (String key : _scores.keySet())
            {
                UserScore score = _scores.get(key);

                Date now = new Date();
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(now);
                score.setLastGameTimestamp(timestamp);
                
                sb.append(score.getUsername());
                sb.append(_separator);
                sb.append(score.getLastGameTimestamp());
                sb.append(_separator);
                sb.append(score.getClassicScore());
                sb.append(_separator);
                sb.append(score.getArcadeScore());
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
    public UserScore GetUserScore(String username)
    {
        UserScore score = null;
        
        if (_scores.containsKey(username))
        {
            score = _scores.get(username);
        }
        
        return score;
    }
    
    public int GetUserPosition(String username)
    {
        int position = 0;
        
        if (_scores.containsKey(username))
        {
            Set set = _scores.entrySet();
            Iterator iterator = set.iterator();
            int index = 0;
            while(iterator.hasNext()) 
            {
                Map.Entry mentry = (Map.Entry)iterator.next();
                if (username.equals(mentry.getValue()))
                {
                    position = index;
                    break;
                }

                index++;
            }
        }        
        
        return position;
    }
    
    
    public void AddUserScore(UserScore score) throws Exception
    {
        if (_scores.containsKey(score.getUsername()))
        {
            throw new Exception("Username score record already exists");
        }
        
        _scores.put(score.getUsername(), score);
    }

    public void UpdateUserScore(UserScore score)
    {
        if (_scores.containsKey(score.getUsername()))
        {
            _scores.put(score.getUsername(), score);
        }
    }

    
    public void DeleteUserScore(String username)
    {
        if (_scores.containsKey(username))
        {
            _scores.remove(username);
        }
    }
    
    
    
}
