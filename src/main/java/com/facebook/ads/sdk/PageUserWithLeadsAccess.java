/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class PageUserWithLeadsAccess extends APINode {
  @SerializedName("active_on_business")
  private Boolean mActiveOnBusiness = null;
  @SerializedName("business_role")
  private String mBusinessRole = null;
  @SerializedName("can_access_leads")
  private Boolean mCanAccessLeads = null;
  @SerializedName("page_role")
  private String mPageRole = null;
  protected static Gson gson = null;

  public PageUserWithLeadsAccess() {
  }

  public String getId() {
    return null;
  }
  public static PageUserWithLeadsAccess loadJSON(String json, APIContext context, String header) {
    PageUserWithLeadsAccess pageUserWithLeadsAccess = getGson().fromJson(json, PageUserWithLeadsAccess.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(pageUserWithLeadsAccess.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      }
    }
    pageUserWithLeadsAccess.context = context;
    pageUserWithLeadsAccess.rawValue = json;
    pageUserWithLeadsAccess.header = header;
    return pageUserWithLeadsAccess;
  }

  public static APINodeList<PageUserWithLeadsAccess> parseResponse(String json, APIContext context, APIRequest request, String header) throws MalformedResponseException {
    APINodeList<PageUserWithLeadsAccess> pageUserWithLeadsAccesss = new APINodeList<PageUserWithLeadsAccess>(request, json, header);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          pageUserWithLeadsAccesss.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
        };
        return pageUserWithLeadsAccesss;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                pageUserWithLeadsAccesss.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            pageUserWithLeadsAccesss.setPaging(previous, next);
            if (context.hasAppSecret()) {
              pageUserWithLeadsAccesss.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              pageUserWithLeadsAccesss.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context, header));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  pageUserWithLeadsAccesss.add(loadJSON(entry.getValue().toString(), context, header));
                }
                break;
              }
            }
            if (!isRedownload) {
              pageUserWithLeadsAccesss.add(loadJSON(obj.toString(), context, header));
            }
          }
          return pageUserWithLeadsAccesss;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              pageUserWithLeadsAccesss.add(loadJSON(entry.getValue().toString(), context, header));
          }
          return pageUserWithLeadsAccesss;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              pageUserWithLeadsAccesss.add(loadJSON(value.toString(), context, header));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return pageUserWithLeadsAccesss;
          }

          // Sixth, check if it's pure JsonObject
          pageUserWithLeadsAccesss.clear();
          pageUserWithLeadsAccesss.add(loadJSON(json, context, header));
          return pageUserWithLeadsAccesss;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public Boolean getFieldActiveOnBusiness() {
    return mActiveOnBusiness;
  }

  public PageUserWithLeadsAccess setFieldActiveOnBusiness(Boolean value) {
    this.mActiveOnBusiness = value;
    return this;
  }

  public String getFieldBusinessRole() {
    return mBusinessRole;
  }

  public PageUserWithLeadsAccess setFieldBusinessRole(String value) {
    this.mBusinessRole = value;
    return this;
  }

  public Boolean getFieldCanAccessLeads() {
    return mCanAccessLeads;
  }

  public PageUserWithLeadsAccess setFieldCanAccessLeads(Boolean value) {
    this.mCanAccessLeads = value;
    return this;
  }

  public String getFieldPageRole() {
    return mPageRole;
  }

  public PageUserWithLeadsAccess setFieldPageRole(String value) {
    this.mPageRole = value;
    return this;
  }




  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public PageUserWithLeadsAccess copyFrom(PageUserWithLeadsAccess instance) {
    this.mActiveOnBusiness = instance.mActiveOnBusiness;
    this.mBusinessRole = instance.mBusinessRole;
    this.mCanAccessLeads = instance.mCanAccessLeads;
    this.mPageRole = instance.mPageRole;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<PageUserWithLeadsAccess> getParser() {
    return new APIRequest.ResponseParser<PageUserWithLeadsAccess>() {
      public APINodeList<PageUserWithLeadsAccess> parseResponse(String response, APIContext context, APIRequest<PageUserWithLeadsAccess> request, String header) throws MalformedResponseException {
        return PageUserWithLeadsAccess.parseResponse(response, context, request, header);
      }
    };
  }
}